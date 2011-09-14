/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mort.mineralvein;
import org.bukkit.util.config.ConfigurationNode;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Biome;
/**
 *
 * @author Martin
 */  
public class OreVein {
        public int block;
        public int seed;
        public double density;
        public double maxSpan;
        public double densBonus;
        public double areaHeight;
        public double areaSpan;
        public double heightLength;
        public double densLength;
        public boolean exclusive;
        public Biome[] biomes;
        
        public static OreVein[] loadConf( List<ConfigurationNode> list ){
            OreVein[] ret = new OreVein[list.size()];
            for(int i=0;i<list.size();i++){
                ConfigurationNode nd = list.get(i);
                ret[i] = new OreVein();
                ret[i].block = MineralVein.readMaterial(nd, "block", Material.STONE).getId();
                ret[i].seed = nd.getInt("seed", 6516);
                ret[i].density = nd.getDouble("density", 1);
                ret[i].maxSpan = nd.getDouble("thickness", 5);
                ret[i].densBonus = nd.getDouble("densityBonus", 0);
                ret[i].areaHeight = nd.getDouble("heightAvg", 32);
                ret[i].areaSpan = nd.getDouble("heightVar", 20);
                ret[i].heightLength = nd.getDouble("heightLength", 80);
                ret[i].densLength = nd.getDouble("densLength", 80);
                ret[i].exclusive = nd.getBoolean("exclusive", false);
                if(nd.getProperty("biomes")!=null){
                    //System.out.println("LOADING BIOMES LIST"+nd.getProperty("biomes")+": "+nd.getStringList("biomes", null)+"; "+nd.getString("biomes"));
                    ret[i].biomes = convertStringList( nd.getStringList("biomes", null) );}
                else ret[i].biomes = null;
                
            }
            
            return ret;
        }
        
        public static Biome[] convertStringList( List<String> list ){
            if( list.isEmpty() )
                return null;
            Biome[] ret = new Biome[list.size()];
            System.out.println("Size:"+list.size() );
            int i = 0;
            for(String str:list){
                try{
                    ret[i] = Biome.valueOf( str.toUpperCase() );
                    i++;
                }catch (Exception e){}
            }
            return ret;
        }
        
}
