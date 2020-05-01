# jokenpo

Programa JoKenPo

Descrição do mecanismo (lógica) usada pelo programa:
Todos as jogadas cadastradas são inicialmente configuradas como vencedoras no seu status. 
Quando a API jogar é acionada a collection de jogadas é percorrida e para cada jogada é verificado 
se exise na collection uma outra jogada que vence ela. Se isto acontecer a jogada é convertida para não vencedora.  
A jogada que sobrar no final sem alteração de status será a vencedora. Se sobrar mais de uma haverá empate. 

