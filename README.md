# SudokuSolver - Trabalho 1 da Disciplina de Inteligência Artificial.

Sudoku Solver desenvolvido em Java a partir de busca desinformada e informada.

# Alunos:
	André Levi Zanon				        RA: 619922
	Rodrigo Ferrari de Souza		    RA: 619795
	Victor Hugo Domingos de Abreu	  RA: 619841
	
# Instruções para compilar e executar o sudoku:
	No Windows:
		No terminal navegue até a pasta src do projeto.
		Execute o comando: javac Sudoku.java para compilar
		Execute o comando: java Sudoku para rodar o programa.
	No Linux:
    Necessário ter instalado openjfx(caso não esteja instalado basta rodar o comando: sudo apt-get install openjfx) devido a biblioteca javafx.util.Pair;
		No terminal navegue até a pasta src do projeto.
		Execute o comando javac Sudoku.java para compilar
		Execute o comando: java Sudoku para rodar o programa.
	
# Busca informada elaborada neste projeto:
	Para implementar a busca informada no programa que resolve Sudoku, a ideia utilizada baseia-se na transformação da busca em profundidade em um algoritmo guloso best-first. Para isso, são analisados todos os números já presentes na matriz do sudoku completo e calculado a quantidade de vezes que cada número apareceu. Diante disso, os números são ordenados a partir daqueles que aparecem mais vezes, pois terão prioridade de serem uma possibilidade de escolha ao se deparar com um espaço vazio. A ideia é que se um número já apareceu muitas vezes, fica mais fácil encontrar o espaço vazio em que ele deve ser colocado. Assim, há um ganho de otimização em relação a busca desinformada feita, já que no caso dela, em cada espaço vazio são verificados todas as possibilidades possíveis para aquele local e para seus filhos. 
	
# Número de iterações em cada tipo de busca:
	*Busca Informada (Best-first/guloso)
		1- Muito Fácil: 5
		2- Fácil: 14
		3- Médio: 465
		4- Difícil: 595
		5- Muito Difícil: 7735
	*Busca Desinformada (em profundidade)
		1- Muito Fácil: 5
		2- Fácil: 15
		3- Médio: 2109
		4- Difícil: 574
		5- Muito Difícil: 9487
	*Busca Desinformada (em largura)
		1- Muito Fácil: 5
		2- Fácil: 15
		3- Médio: 2282
		4- Difícil: 1980
		5- Muito Difícil: 15453
