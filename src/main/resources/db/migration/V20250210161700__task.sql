CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    date        DATE    NOT NULL,
    description TEXT,
    user_id     BIGSERIAL NOT NULL,
    done        BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX task_date_idx ON task (date);
CREATE INDEX task_done_idx ON task (done);