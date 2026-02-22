INSERT INTO table_roles (role_id, name) VALUES (1, 'SUPER_ADMIN'),
                                               (2, 'ADMIN'),
                                               (3, 'EMPLOYEE'),
                                               (4, 'CLIENT') ON CONFLICT(role_id) DO NOTHING;