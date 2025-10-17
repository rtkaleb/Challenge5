-- Inserta una orden de ejemplo al iniciar (H2)
INSERT INTO orders (id, customer_name, customer_email, total_amount, status, created_at, updated_at)
VALUES (RANDOM_UUID(), 'Jane Doe', 'jane@example.com', 199.90, 'NEW', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
