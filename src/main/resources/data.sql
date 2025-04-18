INSERT INTO users (id, username, password, role)
VALUES (1, 'admin', '$2a$10$bu3mhHv1IYjhvyIdLK7qI.OLYMDRTlQ85YZCHzK.7AqkHLXDnwUm2', 'ADMIN');

-- Insert mints
INSERT INTO mint (id, country) VALUES (1, 'United Kingdom');
INSERT INTO mint (id, country) VALUES (2, 'United States');
INSERT INTO mint (id, country) VALUES (3, 'Canada');

-- Insert coins
INSERT INTO coin (name, description, coin_year, metal, weight, price, fineness, mint_id)
VALUES
    ('American Eagle', 'Iconic US gold bullion coin', 2022, 'Gold', 1.0, 1900.0, 0.9167, 2),
    ('Maple Leaf', 'Canadian silver investment coin', 2023, 'Silver', 1.0, 28.0, 0.9999, 3),
    ('Sovereign', 'Classic British gold coin', 2021, 'Gold', 0.2354, 450.0, 0.9167, 1),
    ('Silver Eagle', 'Popular American silver coin', 2022, 'Silver', 1.0, 29.5, 0.999, 2),
    ('Gold Maple Leaf', 'Canadian gold investment coin', 2023, 'Gold', 1.0, 1920.0, 0.9999, 3),
    ('Lunar Series Dragon', 'British gold coin from Lunar Series', 2024, 'Gold', 1.0, 1950.0, 0.9999, 1),
    ('Buffalo', 'Pure gold bullion coin from US Mint', 2023, 'Gold', 1.0, 1980.0, 1.0, 2),
    ('Canadian Wildlife Series: Moose', 'Silver collector coin', 2020, 'Silver', 1.0, 27.0, 0.9999, 3);