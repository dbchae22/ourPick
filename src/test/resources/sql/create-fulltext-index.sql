ALTER TABLE items ADD FULLTEXT INDEX idx_fulltext (item_name, store_name);
