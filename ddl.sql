CREATE TABLE application(
    application_id VARCHAR(30) PRIMARY KEY,
    status VARCHAR(30)
);

CREATE TABLE member(
    member_id VARCHAR(30) PRIMARY KEY,
    first_name VARCHAR(30),
    middle_name VARCHAR(30),
    last_name VARCHAR(30),
    gender VARCHAR(10),
    date_of_birth DATE,
    is_head_member BOOLEAN,
    application_id VARCHAR(30),
    CONSTRAINT fk_application FOREIGN KEY(application_id) REFERENCES application(application_id)

);


CREATE TABLE relationship(
    id VARCHAR(30) PRIMARY KEY,
    first_member VARCHAR(30),
    second_member VARCHAR(30),
    relationship VARCHAR(30),

    CONSTRAINT fk_firstmem FOREIGN KEY(first_member) REFERENCES member(member_id),
    CONSTRAINT fk_secondmem FOREIGN KEY(second_member) REFERENCES member(member_id)
);


