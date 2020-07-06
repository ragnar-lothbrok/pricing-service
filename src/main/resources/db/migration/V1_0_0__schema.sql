-- This table contains subscription types. Check insert queries for more details.
CREATE TABLE subscriptions_type (
    id bigserial NOT NULL PRIMARY KEY,
    time_unit VARCHAR(6) NOT NULL,
    time_unit_value integer NOT NULL,
    active BOOLEAN DEFAULT true,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now()
)
WITH (
    OIDS = FALSE
);

-- This table contains Course Price along with the currency and subscription type.
-- Courses Price can very subscription to subscription.
-- Free courses will have zero price value. More rules should be part of Promo Service.
CREATE TABLE course_price (
    course_id bigint NOT NULL,
    currency VARCHAR(6) NOT NULL,
    subscription_id bigint REFERENCES subscriptions_type(id),
    sale_price NUMERIC(10, 2) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    PRIMARY KEY (course_id, currency, subscription_id)
)
WITH (
    OIDS = FALSE
);

-- This contains taxes on the basis of Currencies. We can enable / disable and add any additional tax which will reflect on checkout page.
-- This is independent table just for Taxes.
CREATE TABLE tax_details (
    id bigserial NOT NULL PRIMARY KEY,
    currency VARCHAR(6) NOT NULL,
    label VARCHAR(20) NOT NULL,
    percentage NUMERIC(5, 2) NOT NULL,
    fixed_tax_amount NUMERIC(5, 2) NOT NULL,
    active BOOLEAN DEFAULT true,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    UNIQUE (currency, label)
)
WITH (
    OIDS = FALSE
);

--
CREATE INDEX currency_tax_details ON tax_details (currency);

-- Constraints on a Column Values
ALTER TABLE subscriptions_type
   ADD CONSTRAINT check_time_unit_types
   CHECK (time_unit = 'Minute' OR time_unit = 'Hour' OR time_unit = 'Day' OR time_unit = 'Month' OR time_unit = 'Year');

--
INSERT INTO subscriptions_type(id, time_unit_value, time_unit) VALUES(1, 1, 'Day');
INSERT INTO subscriptions_type(id, time_unit_value, time_unit) VALUES(2, 15, 'Day');
INSERT INTO subscriptions_type(id, time_unit_value, time_unit) VALUES(3, 1, 'Month');
INSERT INTO subscriptions_type(id, time_unit_value, time_unit) VALUES(4, 6, 'Month');
INSERT INTO subscriptions_type(id, time_unit_value, time_unit) VALUES(5, 1, 'Year');

