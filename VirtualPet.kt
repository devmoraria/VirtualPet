import kotlin.random.Random

class VirtualPet(val nome: String) {
    var nivelDeFome = 50
    var nivelFelicidade = 50
    var nivelCansaco = 0
    var nivelSujeira = 0
    var vontadeBanheiro = 0
    var idade = 0
    var moedas = 20 // Saldo inicial
    var jogoAtivo = true

    fun verificarStatus() {
        val faseDaVida = when {
            idade < 15 -> "Filhote 🐣"
            idade < 35 -> "Adulto 🐕"
            else -> "Sênior 🐺"
        }

        println("\n=================================================")
        println("🐾 $nome ($faseDaVida)")
        println("💰 SALDO ATUAL: $moedas moedas") // Moedas em destaque aqui!
        println("🎂 IDADE: $idade / 50")
        println("-------------------------------------------------")
        println("🍗 Fome: $nivelDeFome/100    😊 Felicidade: $nivelFelicidade/100")
        println("😴 Cansaço: $nivelCansaco/100  🧼 Sujeira: $nivelSujeira/100")
        println("🚽 Banheiro: $vontadeBanheiro/100")
        println("=================================================")
    }

    fun comprarEAlimentar() {
        println("\n--- 🛒 LOJA DO PET ---")
        println("Seu saldo: $moedas moedas") // E aqui também para facilitar a compra!
        println("1. Maçã (5 moedas) [-15 Fome]")
        println("2. Ração Premium (15 moedas) [-40 Fome]")
        println("3. Pizza Party (30 moedas) [-60 Fome, +15 Felicidade]")
        println("0. Sair da loja")
        print("Escolha um item: ")

        val opcao = readln()
        if (opcao == "0") return

        when (opcao) {
            "1" -> realizarCompra(5, 15, 0)
            "2" -> realizarCompra(15, 40, 0)
            "3" -> realizarCompra(30, 60, 15)
            else -> println("❌ Opção inválida!")
        }
    }

    private fun realizarCompra(preco: Int, saciedade: Int, alegria: Int) {
        if (moedas >= preco) {
            moedas -= preco
            nivelDeFome -= saciedade
            nivelFelicidade += alegria
            vontadeBanheiro += (saciedade / 2)
            println("✅ Sucesso! Você gastou $preco moedas. Restam $moedas.")
        } else {
            println("⚠️ Moedas insuficientes! Você tem apenas $moedas e precisa de $preco.")
        }
        validarLimites()
    }

    fun brincar() {
        nivelFelicidade += 20
        nivelCansaco += 25
        nivelSujeira += 15
        val ganho = Random.nextInt(8, 20) // Aumentei um pouco o ganho
        moedas += ganho
        println("🎾 $nome se divertiu! Você ganhou $ganho moedas.")
        validarLimites()
    }

    fun descansar() {
        println("Dormir por quantas horas? (1-8): ")
        val horas = readln().toIntOrNull() ?: 0
        nivelCansaco -= (horas.coerceIn(1, 8) * 12.5).toInt()
        nivelDeFome += 5
        println("💤 Zzzzz... Cansaço reduzido.")
        validarLimites()
    }

    fun levarAoBanheiro() { vontadeBanheiro = 0; println("🚽 Banheiro limpo!") }
    fun darBanho() { nivelSujeira = 0; nivelFelicidade += 5; println("🧼 Pet cheiroso!") }

    fun passarTempo() {
        idade += 1
        nivelDeFome += 3
        nivelFelicidade -= 3
        nivelCansaco += 10

        // Eventos aleatórios (10% de chance)
        if (Random.nextInt(1, 11) == 1) {
            val eventos = listOf(
                "achou uma moeda de 10 no chão! 💰",
                "viu um pássaro e ficou feliz! 😊",
                "teve um pesadelo e acordou cansado... 😴"
            )
            val sorteio = Random.nextInt(eventos.size)
            println("\n🎲 EVENTO: $nome ${eventos[sorteio]}")

            when(sorteio) {
                0 -> moedas += 10
                1 -> nivelFelicidade += 15
                2 -> nivelCansaco += 20
            }
        }
        validarLimites()
    }

    private fun validarLimites() {
        nivelDeFome = nivelDeFome.coerceAtLeast(0)
        nivelFelicidade = nivelFelicidade.coerceIn(0, 100)
        nivelCansaco = nivelCansaco.coerceIn(0, 100)
        nivelSujeira = nivelSujeira.coerceAtLeast(0)
        vontadeBanheiro = vontadeBanheiro.coerceAtLeast(0)

        if (idade >= 50) {
            println("\n🏆 VITÓRIA! $nome atingiu a idade máxima com saúde!")
            jogoAtivo = false
        } else if (nivelDeFome >= 100 || nivelCansaco >= 100 || nivelFelicidade <= 0 || nivelSujeira >= 100 || vontadeBanheiro >= 100) {
            println("\n💀 FIM DE JOGO: $nome não aguentou os cuidados.")
            jogoAtivo = false
        }
    }
}

fun main() {
    println("--- BEM-VINDO AO PET SIMULATOR ---")
    print("Nome do seu companheiro: ")
    val pet = VirtualPet(readln().ifBlank { "Baltazar" })

    while (pet.jogoAtivo) {
        println("\nO que deseja fazer?")
        println("1. Alimentar/Loja | 2. Brincar (Ganha Moedas) | 3. Dormir")
        println("4. Banheiro       | 5. Banho                | 6. Status")
        println("7. Sair")
        print(">> ")

        when (readln()) {
            "1" -> pet.comprarEAlimentar()
            "2" -> pet.brincar()
            "3" -> pet.descansar()
            "4" -> pet.levarAoBanheiro()
            "5" -> pet.darBanho()
            "6" -> pet.verificarStatus()
            "7" -> return
            else -> println("Opção inexistente!")
        }
        if (pet.jogoAtivo) pet.passarTempo()
    }
}