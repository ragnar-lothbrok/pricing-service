--
INSERT INTO tax_details(id, currency, label, percentage, fixed_tax_amount) VALUES(1, 'INR', 'SGST', 9.00, 0.00);
INSERT INTO tax_details(id, currency, label, percentage, fixed_tax_amount) VALUES(2, 'INR', 'CGST', 9.00, 0.00);
INSERT INTO tax_details(id, currency, label, percentage, fixed_tax_amount) VALUES(3, 'INR', 'Service Charge', 0.00, 60.00);

INSERT INTO tax_details(id, currency, label, percentage, fixed_tax_amount) VALUES(4, 'USD', 'Service Tax', 5.00, 0.00);
INSERT INTO tax_details(id, currency, label, percentage, fixed_tax_amount) VALUES(5, 'USD', 'Service Charge', 0.00, 6.00);


--
INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(1, 'INR', 3, 100.00, 2000.00);
INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(1, 'INR', 4, 600.00, 12000.00);

INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(1, 'USD', 3, 1.34, 26.78);
INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(1, 'USD', 4, 8.03, 160.67);

INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(2, 'INR', 1, 0.00, 2000.00);
INSERT INTO course_price(course_id, currency, subscription_id, sale_price, price) VALUES(2, 'INR', 5, 500.00, 10000.00);