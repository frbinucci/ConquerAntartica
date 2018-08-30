/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import java.util.LinkedList;

public class PlayerData {

    private LinkedList<Player> playerLst;

    public PlayerData() {
        this.playerLst = new LinkedList<Player>();
    }

    public void add(Player player) {
        this.playerLst.add(player);
    }
    
    public void remove(Player player)
    {
        this.playerLst.remove(player);
    }

    public LinkedList<Player> getListOfPlayers() {
        return this.playerLst;
    }

    public LinkedList<String[]> asListOfStringArray() {
        LinkedList<String[]> lstSA = null;

        lstSA = new LinkedList<String[]>();
        String[] sArr = null;
        for (Player p : this.playerLst) {
            sArr = new String[6];
            sArr[0] = String.valueOf(p.getPlayerId());
            sArr[1] = p.getPlayerName();
            sArr[2] = String.valueOf(p.getPlayerHighScore());
            sArr[3] = String.valueOf(p.getPlayerNumberOfLives());
            sArr[4] = String.valueOf(p.getDifficultyLevel());
            sArr[5] = String.valueOf(p.getCurrentMission());
            lstSA.add(sArr);
        }

        return lstSA;
    }

} // end class
