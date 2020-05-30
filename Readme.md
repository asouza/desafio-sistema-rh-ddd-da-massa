Sistema de Gestão de Recursos Humanos para Prefeituras

1) A ideia seria trabalhar com o contexto de folha de pagamento, onde encontra-se toda a complexidade descrita no e-mail. Minha sugestão é preservar toda a complexidade relacionada aos cálculos de folha de pagamento e simplificar conceitos que geram complexidade não relacionada ao nosso objeto de análise, a fim de facilitar a implementação dos códigos a serem analisados.
Dentro dessa perspectiva, segue abaixo a linguagem ubíqua desse contexto:

- Há UMA e SOMENTE UMA ENTIDADE associada à ADM DIRETA.

- Há ZERO OU MAIS ENTIDADES associadas á ADM INDIRETA

- Os SERVIDORES PUBLICOS são pessoas ocupantes de CARGOS PUBLICOS, que podem ser de natureza ESTATUTARIA ou COMISSIONADA.

- A cada CARGO está associado um valor de SALARIO MENSAL

- Um servidor público não pode assumir mais de um vínculo ativo na mesma entidade.*

* aqui uma simplificação para nossa análise que na prática pode não ocorrer em situações específicas.

- A Folha de Pagamento ocorre mensalmente. Pode haver PROCESSAMENTOS de 2 tipos: REGULAR (obrigatório para todas as entidades e que ocorre UMA ÚNICA VEZ em cada mês) e COMPLEMENTAR (não-obrigatório e que pode ocorrer 0 ou várias vezes no mÊs, podendo ocorrer para certas entidades e podendo não ocorrer para outras)*

*Aqui uma observação: a ocorrência de várias folhas complementares no mês já era uma demanda antiga dos clientes. Essa demanda não havia sido implementada pelo menos até eu sair de lá da empresa. Até então, só havia suporte a 1 folha complementar por entidade/mês. Aqui fica nossa opção de querer suportar várias folhas complementares ou não.

- Cada servidor publico ativo terá associada a si uma MOVIMENTAÇÂO para PROCESSAMENTO de folha de pagamento do tipo regular. No casos das folhas complementares, o servidor poderá ter ou não uma movimentação associada.

- Uma Movimentação é composta por uma coleção de LANCAMENTOS. Cada lançamento possui um EVENTO associado e um valor.

- Cada EVENTO tem uma NATUREZA (VANTAGEM/DESCONTO) e uma FORMULA DE CALCULO.

- Cada servidor público ativo terá, na folha regular, um lançamento de SALARIO, com o valor do SALARIO MENSAL associado ao cargo do servidor.

- Para folhas complementares, não será possível o lançamento do evento de SALARIO

- Cada servidor público deverá ter descontado dos seus vencimentos mensais um valor de contribuição previdenciária segundo a tabela em

https://www.inss.gov.br/servicos-do-inss/calculo-da-guia-da-previdencia-social-gps/tabela-de-contribuicao-mensal/

- A base de cálculo para contribuição previdenciária de cada servidor, chamada SALARIO DE CONTRIBUICAO, será o TOTAL DAS VANTAGENS percebidas no mês pelo servidor, considerando a folha regular e todas as folhas complementares do mÊs.

- Cada servidor público poderá ter descontado dos seus vencimentos mensais um valor de IMPOSTO DE RENDA, que deve ser calculado segundo a tabela em (veja Tabelas de incidência mensal):

http://receita.economia.gov.br/acesso-rapido/tributos/irpf-imposto-de-renda-pessoa-fisica#calculo_mensal_IRPF

- A base de cálculo para o IR de cada servidor deve ser o SOMATORIO de todas as vantagens percebidas no mês pelo servidor (independente se folha REGULAR/COMPLEMENTAR) subtraido dos valores descontados do servidor a titulo de contribuição previdenciária e PENSAO ALIMENTICIA

- Cada servidor, mensalmente, poderá ter ou não um desconto de PENSAO ALIMENTICIA, a depender da existencia ou não de ordem judicial que justifique o desconto. A forma de calculo do desconto de pensão alimentícia pode variar de acordo com o definido pela decisão judicial*.
Eis alguns exemplos:

    a) % sobre vencimentos líquidos (salario + outras vantagens - contrib. prev. - ir)

    b) % sobre vencimentos brutos (salario + outras vantagens)

    c) % sobre salário mínimo

    d) valor fixo

    e) outros (briga de família pode trazer resultados inesperados)

* Aqui, por simplificação, podemos convencionar sempre a opção "a", se você preferir.

- Os somatórios mensal dos valores de cada desconto (considerando folha regular e folha(s) complementar(es)) deve ser calculado pelo somatório das respectivas bases de cálculo considerando folha regular e folha(s) complementar(es).

- Os valores dos descontos lançados em cada processamento deve ser proporcional ao total de vantagens dos mesmos.

- As regras referentes ao cálculo de descontos no caso de mais de uma movimentação no mês para o mesmo servidor por conta de folhas complementares também devem ser observadas de forma idêntica no caso da mesma pessoa possuir vínculos ativos em entidades diferentes.