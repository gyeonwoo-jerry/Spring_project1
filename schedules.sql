create table schedules
(
    id BIGINT auto_increment primary key,
    user_name VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    contents VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    initial_date DATETIME NOT NULL ,
    up_date DATETIME NOT NULL
);