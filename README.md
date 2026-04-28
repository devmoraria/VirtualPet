🐾 Virtual Pet Simulator - Kotlin Edition
Um simulador de animal de estimação virtual desenvolvido em Kotlin que utiliza lógica de estados para gerenciar a vida, o humor e a economia de um companheiro digital. O objetivo é cuidar do seu pet até que ele atinja a idade máxima (50 anos), equilibrando necessidades básicas e recursos financeiros.

🌟 Funcionalidades
📈 Ciclo de Vida Dinâmico: O pet evolui de Filhote para Adulto e Idoso conforme o tempo passa.

💰 Sistema Econômico: Trabalhe ou brinque para ganhar moedas e utilize-as na loja para comprar diferentes tipos de alimentos com efeitos variados.

🧠 IA de Eventos Aleatórios: Ocorrências inesperadas podem acontecer a cada turno (achar moedas, pesadelos, etc.), afetando os atributos do pet.

📊 Status Detalhado: Interface de terminal organizada com alertas de níveis críticos (fome, cansaço, felicidade).

🛠️ Lógica Robusta: Proteção contra atributos negativos ou infinitos através da função coerceIn.

🎮 Como Jogar
O jogo funciona por turnos. A cada ação realizada, o tempo passa e o pet envelhece.

Ações Disponíveis:
Loja/Alimentar: Gaste moedas para reduzir a fome. Alimentos caros dão mais saciedade e felicidade.

Brincar: Aumenta a felicidade e gera algumas moedas, mas suja o pet e causa cansaço.

Dormir: Reduz o cansaço drasticamente baseado nas horas escolhidas.

Higiene: Leve ao banheiro ou dê banho para evitar que o pet adoeça por sujeira.

Trabalhar: A melhor forma de ganhar moedas, porém é a ação que mais consome energia e aumenta a fome.

🛠️ Conceitos Técnicos Aplicados
Encapsulamento: Uso de classes e métodos privados para controle interno de estados.

Controle de Fluxo: Estruturas when e while para gerenciamento do menu e ciclos de vida.

Randomização: Uso da biblioteca kotlin.random.Random para simular incertezas e ganhos financeiros variados.

Tratamento de Dados: Validação de entradas do usuário para evitar que o programa quebre com valores nulos ou inválidos.

⚙️ Requisitos
Kotlin SDK (1.5 ou superior)

JVM (Java Virtual Machine)

Bash
# Para compilar e rodar
kotlinc Main.kt -include-runtime -d PetSimulator.jar
java -jar PetSimulator.jar
💀 Condições de Derrota e Vitória
Vitória 🏆: Chegar aos 50 anos de idade mantendo todos os níveis estáveis.

Derrota 💀: O jogo termina se qualquer atributo (Fome, Cansaço, Sujeira ou Banheiro) atingir 100, ou se a Felicidade chegar a 0.

Desenvolvido como um estudo de lógica e manipulação de objetos em Kotlin.
