Feature: Envio De Mensagem Suporte e Envio de Mensagem

  Background:
    Given que acesso o site Eveclass
    When clico no campo email
    And e digito um email valido
    And clico no campo senha
    And digito uma senha valida e clico no botão entrar

  Scenario: Enviar Mensagem como Admin com Sucesso
    And  clico em suporte
    And  digito uma mensagem no campo Mensagem
    And  clico no botão Enviar
    Then um pop up com a  frase  Mensagem enviada com sucesso é exibida

  Scenario: Alteração de Cobrança no site Eveclass

    And  clico em transações
    And  e clico em alterar cobrança
    Then a tela de alterar cobrança é exibida