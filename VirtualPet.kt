import kotlin.random.Random

class VirtualPet(val nome: String) {
    var nivelDeFome = 50
    var nivelFelicidade = 50
    var nivelCansaco = 0
    var nivelSujeira = 0
    var vontadeBanheiro = 0
    var idade = 0
    var moedas = 20
    var jogoAtivo = true
// Deixando mais bonito <3
    fun verificarStatus() {
        val faseDaVida = when {
            idade < 15 -> "Filhote 🐣"
            idade < 35 -> "Adulto 🐕"
            else -> "Idoso 🐺"
        }

        println("\n=================================================")
        println("🐾 $nome ($faseDaVida) | 💰 SALDO: $moedas moedas")
        println("🎂 IDADE: $idade / 50")

        if (nivelDeFome >= 80) println("⚠️ ALERTA: $nome está faminto! ($nivelDeFome/100)")
        else println("🍗 Fome: $nivelDeFome/100")

        if (nivelCansaco >= 80) println("⚠️ ALERTA: $nome está exausto! ($nivelCansaco/100)")
        else println("😴 Cansaço: $nivelCansaco/100")

        if (nivelFelicidade <= 20) println("⚠️ ALERTA: $nome está ficando deprimido! ($nivelFelicidade/100)")
        else println("😊 Felicidade: $nivelFelicidade/100")

        println("🧼 Sujeira: $nivelSujeira/100 | 🚽 Banheiro: $vontadeBanheiro/100")
        println("=================================================")
    }

    // Opção de trabalho
    fun trabalhar() {
        println("\n💼 Você foi fazer uns bicos para ganhar dinheiro...")
        val ganho = Random.nextInt(25, 45)
        moedas += ganho
        nivelCansaco += 30
        nivelDeFome += 15
        nivelFelicidade -= 10
        println("💰 Ótimo trabalho! Você recebeu $ganho moedas, mas está cansado e faminto.")
        validarLimites()
    }

    fun comprarEAlimentar() {
        println("\n--- 🛒 LOJA DO PET ---")
        println("Seu saldo: $moedas moedas")
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
            println("⚠️ Moedas insuficientes!")
        }
        validarLimites()
    }

    fun brincar() {
        nivelFelicidade += 20
        nivelCansaco += 25
        nivelSujeira += 15
        val ganho = Random.nextInt(8, 20)
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
        // Agora todos os atributos são travados entre 0 e 100 para evitar números negativos ou infinitos
        nivelDeFome = nivelDeFome.coerceIn(0, 100)
        nivelFelicidade = nivelFelicidade.coerceIn(0, 100)
        nivelCansaco = nivelCansaco.coerceIn(0, 100)
        nivelSujeira = nivelSujeira.coerceIn(0, 100)
        vontadeBanheiro = vontadeBanheiro.coerceIn(0, 100)

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
        println("1. Alimentar/Loja | 2. Brincar | 3. Dormir")
        println("4. Banheiro       | 5. Banho   | 6. Status")
        println("7. TRABALHAR 💰   | 8. Sair")
        print(">> ")

        when (readln()) {
            "1" -> pet.comprarEAlimentar()
            "2" -> pet.brincar()
            "3" -> pet.descansar()
            "4" -> pet.levarAoBanheiro()
            "5" -> pet.darBanho()
            "6" -> pet.verificarStatus()
            "7" -> pet.trabalhar()
            "8" -> return
            else -> println("Opção inexistente!")
        }
        if (pet.jogoAtivo) pet.passarTempo()
    }
}