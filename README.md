﻿ # Teste Técnico - Desenvolvimento de uma API para a entidade "Conta" (bill - billpayment)

## Executando a API via container:

docker-compose up

## Serviços e payloads de exemplo:

### Criando uma conta a pagar:

/api/billpayment/bill

```
{
  "description": "Conta de Luz"
  "amount": 500.50,
  "dueDate": 2024-08-05
  "status": 0  // 0 - Opened (Aberto) 1 - Payed (Pago)
}
```

### Busca de uma conta por ID:

/api/billpayment/bill/{id}
 
### Busca de uma conta por descrição e data de vencimento:

/api/billpayment/bill?description={decription}&dueDate={dueDate}

### Atualizar uma conta:

/api/billpayment/bill/{id}

```
{
    "amount": 165.90,
    "paymentDate": "2024-08-15",
    "status": 1
}
```

### Upload de contas via arquivo .csv:

/api/billpayment/bill/upload

Exemplo de arquivo:

```
description,amount,dueDate
Visa,1200,2024-08-20
Internet,100,2024-08-01
Vivo,110.50,2024-08-10
```

## TODO List:

Algumas melhorias podem ser aplicadas ao código desenvolvido:

- [ ] Melhorar testes unitários. Nesta versão foram testados apenas 2 serviços em seu fluxo de happy day
- [ ] Melhorar tratamento de exceções, evitando que a pilha de execção apareça
- [ ] Melhorar a tratativa de alguns fluxos alternativos, como validação de arquivo, etc
- [ ] Implementar algum mecanisco de segurança nos serviços, para que os mesmos possam ser chamados após autenticação.
