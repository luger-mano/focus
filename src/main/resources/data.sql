INSERT INTO table_roles (role_id, name) VALUES (1, 'ADMIN'),
                                               (2, 'EMPLOYEE') ON CONFLICT(role_id) DO NOTHING;