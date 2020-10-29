CREATE TABLE todoItem
(
    id               bigint           NOT NULL PRIMARY KEY auto_increment,
    todo_text        VARCHAR(30)      NOT NULL,
    done             boolean          NOT NULL
);