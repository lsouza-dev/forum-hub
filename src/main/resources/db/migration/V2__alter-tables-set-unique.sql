ALTER TABLE topicos
ADD CONSTRAINT titulo_unique UNIQUE (titulo),
ADD CONSTRAINT mensagem_unique UNIQUE (mensagem);
