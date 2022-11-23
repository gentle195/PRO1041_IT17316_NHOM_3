package ViewModels;


import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dinhq
 */
public class SizeViewModel {

    private UUID IdSize;
    private String MaSize;
    private int SoSize;
  

    public SizeViewModel() {
    }

    public SizeViewModel(UUID IdSize, String MaSize, int SoSize) {
        this.IdSize = IdSize;
        this.MaSize = MaSize;
        this.SoSize = SoSize;
    }

    public UUID getIdSize() {
        return IdSize;
    }

    public void setIdSize(UUID IdSize) {
        this.IdSize = IdSize;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public int getSoSize() {
        return SoSize;
    }

    public void setSoSize(int SoSize) {
        this.SoSize = SoSize;
    }

  
    
}
