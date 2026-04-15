
-- todo: remove the hard-coded test passwords.
INSERT INTO
    users(id, email, password, role)

VALUES
    (1, 'john.doe', '$2a$10$2Fi5IhQGUl3UOovID2Cr/ucMTOvTaw0I1nLD5seAZhKJGrCeToJxu', 'ROLE_USER'),
    (2, 'jane.doe', '$2a$10$/iHBuawtih6pnOiWOR9UJeQgvZxuKuRT1dDP2OEnC0wDrcYBnU1Le', 'ROLE_USER');