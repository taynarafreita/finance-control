CREATE TABLE users (
	id UUID PRIMARY KEY NOT NULL,
	name VARCHAR(250) NOT NULL,
	email VARCHAR(250) NOT NULL,
	username VARCHAR(250) NOT NULL,
	password VARCHAR(250) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL
);

CREATE TABLE categories (
	id SERIAL PRIMARY KEY NOT NULL,
	description VARCHAR(250) NOT NULL
);

CREATE TABLE accounts_balance (
	id UUID PRIMARY KEY NOT NULL,
	user_id UUID NOT NULL,
	current_balance DECIMAL NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transactions (
	id UUID PRIMARY KEY NOT NULL,
	user_id UUID NOT NULL,
	transaction_description VARCHAR(250) NOT NULL,
	category_id INT NOT NULL,
	expense_type VARCHAR(250) NOT NULL,
	due_date DATE NULL,
	amount DECIMAL NOT NULL,
	comments VARCHAR(250) NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO categories (description) VALUES
    ('Animais de Estimação'),
    ('Comida e Bebida'),
    ('Compras'),
    ('Contas'),
    ('Cuidados Pessoais'),
    ('Diversos'),
    ('Educação'),
    ('Empréstimos'),
    ('Entretenimento'),
    ('Esportes'),
    ('Impostos'),
    ('Investimentos'),
    ('Mercado'),
    ('Moradia'),
    ('Outros Gastos'),
    ('Saúde'),
    ('Seguros'),
    ('Serviços'),
    ('Tarifas Financeiras'),
    ('Transferências'),
    ('Transporte'),
    ('Viagem'),
    ('Restituição de Impostos'),
    ('Salário'),
    ('Férias'),
    ('Bônus'),
    ('Devolução'),
    ('Outras Receitas');