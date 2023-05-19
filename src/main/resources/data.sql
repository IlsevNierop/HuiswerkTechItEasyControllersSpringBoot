INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (1, 'LED', 'Samsung', 'Samsung Smart TV', 999.99, 55.0, 120.0, 'Flat', '4K Ultra HD', true, true, true, true, false, 50, 20);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (2, 'OLED', 'LG', 'LG OLED TV', 1499.99, 65.0, 240.0, 'Curved', '4K Ultra HD', true, true, true, true, true, 30, 10);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (3, 'QLED', 'Sony', 'Sony QLED TV', 1299.99, 75.0, 120.0, 'Flat', '8K Ultra HD', true, false, true, true, false, 40, 15);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (4, 'LED', 'TCL', 'TCL Smart TV', 699.99, 50.0, 60.0, 'Flat', 'Full HD', true, true, false, false, false, 60, 25);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (5, 'LED', 'Hisense', 'Hisense LED TV', 599.99, 43.0, 60.0, 'Flat', '4K Ultra HD', false, false, false, true, true, 70, 35);

INSERT INTO ci_modules (id, name, type, price)
VALUES (1, 'CAM Module', 'Standard', 29.99);

INSERT INTO ci_modules (id, name, type, price)
VALUES (2, 'CI+ Module', 'HD+', 39.99);

INSERT INTO ci_modules (id, name, type, price)
VALUES (3, 'CI Module', 'SD', 19.99);

INSERT INTO ci_modules (id, name, type, price)
VALUES (4, 'Conditional Access Module', '4K', 49.99);

INSERT INTO ci_modules (id, name, type, price)
VALUES (5, 'MultiCAM Module', 'Multi-room', 59.99);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (1, 'Television', 'AAA', 'Universal TV Remote', 'Sony', 19.99, 50);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (2, 'Television', 'AA', 'Smart TV Remote', 'Samsung', 14.99, 40);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (3, 'Set-Top Box', 'AAA', 'STB Remote Control', 'LG', 9.99, 60);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (4, 'Audio System', 'AA', 'Soundbar Remote', 'Bose', 24.99, 30);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (5, 'Television', 'AAA', 'LED TV Remote', 'Panasonic', 12.99, 50);

INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (1, '32-55 inch', true, 'Full Motion TV Wall Mount', 59.99);

INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (2, '40-70 inch', true, 'Tilting Wall Mount Bracket', 39.99);

INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (3, '26-50 inch', false, 'Fixed TV Wall Bracket', 19.99);

INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (4, '42-80 inch', true, 'Articulating Wall Mount', 79.99);

INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (5, '37-63 inch', true, 'Swivel TV Mount Bracket', 49.99);