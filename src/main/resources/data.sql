-- Messages
-- INSERT INTO 
--     messages(content)
-- VALUES('Hello from PostgreSQL');

-- Users
-- Fake user passwords: 
-- - 'test123' for 'john.doe'
-- - 'user123' for 'jane.doe'
INSERT INTO
    users(email, password, role)

VALUES
    ('john.doe', '$2a$10$2Fi5IhQGUl3UOovID2Cr/ucMTOvTaw0I1nLD5seAZhKJGrCeToJxu', 'ROLE_USER'),
    ('jane.doe', '$2a$10$/iHBuawtih6pnOiWOR9UJeQgvZxuKuRT1dDP2OEnC0wDrcYBnU1Le', 'ROLE_USER');