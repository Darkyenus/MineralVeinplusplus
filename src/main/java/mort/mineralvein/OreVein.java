
package mort.mineralvein;

import org.bukkit.block.Biome;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.List;
import java.util.Map;

/** @author Martin */
public class OreVein {
	public MVMaterial material;
	public int seed;
	public double density;
	public double thickness;
	public double densBonus;
	public double heightAvg;
	public double heightVar;
	public double heightLength;
	public double densLength;
	public boolean addMode;
	public boolean heightRel;
	public Biome[] biomes;
	public Biome[] noBiomes;

	public static OreVein[] loadConf (List<Map<?, ?>> lst) {
		OreVein[] ret = new OreVein[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			MemoryConfiguration nd = new MemoryConfiguration();
			nd.createSection("sec", lst.get(i));
			ret[i] = new OreVein();
			ret[i].material = new MVMaterial(nd.getString("sec.block", "0"));
			ret[i].seed = nd.getInt("sec.seed", ret[i].material.id);
			ret[i].density = nd.getDouble("sec.density", 1);
			ret[i].thickness = nd.getDouble("sec.thickness", 5);
			ret[i].densBonus = nd.getDouble("sec.densityBonus", 0);
			ret[i].heightAvg = nd.getDouble("sec.heightAvg", 32);
			ret[i].heightVar = nd.getDouble("sec.heightVar", 20);
			ret[i].heightRel = nd.getBoolean("sec.heightRel", false);
			ret[i].heightLength = nd.getDouble("sec.heightLength", 80);
			ret[i].densLength = nd.getDouble("sec.densLength", 80);
			ret[i].addMode = nd.getString("sec.mode", "").equals("add");
			if (nd.contains("sec.biomes")) {
				ret[i].biomes = convertStringList(nd.getStringList("sec.biomes"));
			} else {
				ret[i].biomes = null;
			}
			if (nd.contains("sec.exclude_biomes")) {
				ret[i].noBiomes = convertStringList(nd.getStringList("sec.exclude_biomes"));
			} else {
				ret[i].noBiomes = null;
			}
			if (MineralVein.plugin.debug) {
				System.out.println("LOADED ORE: " + ret[i].material.id);
			}
		}

		return ret;
	}

	private static Biome[] convertStringList (List<String> list) {
		if (list.isEmpty()) {
			return null;
		}
		Biome[] ret = new Biome[list.size()];
		System.out.println("Size:" + list.size());
		int i = 0;
		for (String str : list) {
			try {
				ret[i] = Biome.valueOf(str.toUpperCase());
				i++;
			} catch (Exception ignored) {
			}
		}
		return ret;
	}
}
