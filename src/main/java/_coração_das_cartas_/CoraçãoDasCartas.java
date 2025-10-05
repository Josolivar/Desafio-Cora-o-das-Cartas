package _coração_das_cartas_;
/*
Desafio
Marcos decidiu abandonar o bar da faculdade onde jogava truco para dedicar-se ao mundo da programação. Para que isso fosse mais fácil, decidiu criar um novo jogo de cartas. 
O coração das cartas, como Marcos apelidou o jogo, é individual e jogado com três pilhas, inicialmente com o mesmo número de cartas. Cada carta tem um valor numérico inteiro de 0 até 9. O jogador pode, a qualquer momento ver o valor de qualquer carta, mas só pode jogar com as cartas que estão no topo das pilhas. Em cada rodada, o jogador pode remover qualquer combinação de cartas que estejam no topo da pilha (pode escolher 1, 2 ou até 3 cartas) cuja soma dos valores seja múltiplo de 3. O jogo é ganho quando todas as cartas forem removidas das pilhas. Se alguma carta não puder ser removida, perde-se o jogo.
Entrada
A entrada é composta por várias instâncias Cada instância é iniciada por um inteiro N (0 ≤ N ≤ 100), que identifica o número de cartas em cada pilha. A entrada termina quando N = 0. Cada uma das N linhas seguintes contém três inteiros A, B e C, que descrevem os valores numéricos das cartas em um nível da pilha (0 ≤ A, B, C ≤  9). As pilhas são descritas do topo até o fundo.
Saída
Para cada instância, imprima uma linha contendo o número 1 se o jogador pode ganhar a instância do jogo ou o número 0 se o jogo for impossível.
@author josol
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class CoraçãoDasCartas {
    
   public static void main(String[] args) throws IOException {
      
      BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
      int[][] pilhas = new int[3][]; 
      String entrada;
      int numeroDeCartasPorPilha, soma, i, j, k;
      String[] auxiliar;
      ArrayList<Integer> saida = new ArrayList<>(); 
      HashSet<String> tentativasFrustradas = new HashSet<>();
       
      while(!(entrada = leitor.readLine()).equals("0")) {
         numeroDeCartasPorPilha = Integer.parseInt(entrada);
         soma = i = j = k = 0;
         for(int n = 0; n < 3; n++) 
            pilhas[n] = new int[numeroDeCartasPorPilha + 1];
         while(0 < numeroDeCartasPorPilha--) {
            auxiliar = leitor.readLine().split(" "); 
            if((pilhas[0][i] = Integer.parseInt(auxiliar[0])%3) != 0) 
               soma += pilhas[0][i++];
            if((pilhas[1][j] = Integer.parseInt(auxiliar[1])%3) != 0) 
               soma += pilhas[1][j++];
            if((pilhas[2][k] = Integer.parseInt(auxiliar[2])%3) != 0) 
               soma += pilhas[2][k++];                                    
         } 
         if(soma % 3 != 0)  
            saida.add(0);  
         else
            saida.add(testa(pilhas, 0, 0, 0, tentativasFrustradas)); 
         tentativasFrustradas.clear();
      }
       
      saida.forEach(System.out::println); 
   }     

   static private int testa(int[][] pilhass, int i, int j, int k, HashSet<String> tentativasFrustradas) { 
      
      if(tentativasFrustradas.contains("" + i + j + k)) 
         return 0;                                               
        
      while(true) {
          
         if(pilhass[0][i] + pilhass[1][j] + pilhass[2][k] == 0) 
            return 1;   
              
         if(pilhass[0][i] == pilhass[1][j] && pilhass[0][i] == pilhass[2][k]) {
            i++; 
            j++; 
            k++;
            continue;
         } 
             
         if((pilhass[0][i] + pilhass[1][j] == 3) && (testa(pilhass, i+1, j+1, k, tentativasFrustradas) == 1))
            return 1;                                                                                           
               
         if((pilhass[0][i] + pilhass[2][k] == 3) && (testa(pilhass, i+1, j, k+1, tentativasFrustradas) == 1))
            return 1; 
              
         if((pilhass[1][j] + pilhass[2][k] == 3) && (testa(pilhass, i, j+1, k+1, tentativasFrustradas) == 1))
            return 1;
              
         tentativasFrustradas.add("" + i + j + k);
         return 0;
      }    
   }   
}