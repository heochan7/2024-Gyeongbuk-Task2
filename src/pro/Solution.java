package pro;

public class Solution {
    public int solution(String[] board) {
        String arr[][] = new String[3][3];
        
        int answer = -1;
        
        for( int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                arr[i][j] = board[i].substring(j, j+1);
            }
        }
        return answer;
    }
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.solution(new String[]{"O.X", ".O.", "..X"});
	}

}
