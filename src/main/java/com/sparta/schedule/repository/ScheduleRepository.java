package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleRepository {

    private JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedules (user_name, title, contents, password, initial_date, up_date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getUsername());
                    preparedStatement.setString(2, schedule.getTitle());
                    preparedStatement.setString(3, schedule.getContents());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getInitialDate()));
                    preparedStatement.setTimestamp(6, Timestamp.valueOf(schedule.getUpDate()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        return schedule;
    }

    public List<ScheduleResponseDto> findAll(LocalDateTime afterDate) {
        // DB 조회
        String sql = "SELECT * FROM schedules";

        if (afterDate != null) {
            sql += " WHERE up_date > ? ORDER BY up_date DESC";
            return jdbcTemplate.query(sql, new Object[]{afterDate}, this::mapScheduleResponseDto);
        } else {
            // afterDate가 없으면 모든 데이터를 조회
            return jdbcTemplate.query(sql, this::mapScheduleResponseDto);
        }
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedules SET user_name = ?, contents = ?, title = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), requestDto.getTitle(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedules WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setUsername(resultSet.getString("user_name"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setTitle(resultSet.getString("title"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
    // 중복되는 부분을 메서드로 분리
    private ScheduleResponseDto mapScheduleResponseDto(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String username = rs.getString("user_name");
        String contents = rs.getString("contents");
        String title = rs.getString("title");
        LocalDateTime initialdate = rs.getTimestamp("initial_date").toLocalDateTime();
        LocalDateTime update = rs.getTimestamp("up_date").toLocalDateTime();

        return new ScheduleResponseDto(id, username, contents, title, initialdate, update);
    }
}
