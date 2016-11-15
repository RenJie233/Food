package com.example.dllo.food.entity;

import java.util.List;

/**
 * Created by Ren on 16/11/15.
 */
public class FoodDetailBean {

    /**
     * id : 910
     * code : jidan_junzhi
     * name : 鸡蛋
     * calory : 144
     * weight : 100
     * health_light : 1
     * is_liquid : false
     * thumb_image_url : http://s.boohee.cn/house/food_mid/mid_photo_2015131131916910.jpg
     * large_image_url : http://s.boohee.cn/house/food_big/big_photo2015131131916910.jpg
     * uploader :
     * appraise : 鸡蛋热量及营养与生鸡蛋基本相同，但熟鸡蛋中的营养物质被人体吸收利用效率更高。
     * protein : 13.3
     * fat : 8.8
     * fiber_dietary : 0.0
     * carbohydrate : 2.8
     * gi :
     * gl :
     * compare : {}
     * recipe : false
     * recipe_type : null
     * units : [{"unit_id":34,"amount":"1.0","unit":"只(中)","weight":"60.0","eat_weight":"52.8","calory":"86.4"},{"unit_id":210,"amount":"1.0","unit":"只（大）","weight":"70.0","eat_weight":"61.6","calory":"100.8"}]
     * ingredient : {"calory":"144.0","protein":"13.3","fat":"8.8","carbohydrate":"2.8","fiber_dietary":"","vitamin_a":"234.0","vitamin_c":"","vitamin_e":"1.8","carotene":"","thiamine":"0.1","lactoflavin":"0.3","niacin":"0.2","cholesterol":"585.0","magnesium":"10.0","calcium":"56.0","iron":"2.0","zinc":"1.1","copper":"0.2","manganese":"0.0","kalium":"154.0","phosphor":"130.0","natrium":"131.5","selenium":"14.3"}
     * lights : {"calory":"低热量","protein":"高蛋白","carbohydrate":"","fat":"","fiber_dietary":"","gi":"","gl":""}
     * comments_ct : 47
     * health_appraise : [{"health_mode":0,"show":1,"light":1,"appraise":"鸡蛋热量及营养与生鸡蛋基本相同，但熟鸡蛋中的营养物质被人体吸收利用效率更高。"},{"health_mode":1,"show":1,"light":1,"appraise":"鸡蛋热量及营养与生鸡蛋基本相同，但熟鸡蛋中的营养物质被人体吸收利用效率更高。"}]
     */

    private int id;
    private String code;
    private String name;
    private String calory;
    private String weight;
    private int health_light;
    private boolean is_liquid;
    private String thumb_image_url;
    private String large_image_url;
    private String uploader;
    private String appraise;
    private String protein;
    private String fat;
    private String fiber_dietary;
    private String carbohydrate;
    private String gi;
    private String gl;
    private boolean recipe;
    private Object recipe_type;
    /**
     * calory : 144.0
     * protein : 13.3
     * fat : 8.8
     * carbohydrate : 2.8
     * fiber_dietary :
     * vitamin_a : 234.0
     * vitamin_c :
     * vitamin_e : 1.8
     * carotene :
     * thiamine : 0.1
     * lactoflavin : 0.3
     * niacin : 0.2
     * cholesterol : 585.0
     * magnesium : 10.0
     * calcium : 56.0
     * iron : 2.0
     * zinc : 1.1
     * copper : 0.2
     * manganese : 0.0
     * kalium : 154.0
     * phosphor : 130.0
     * natrium : 131.5
     * selenium : 14.3
     */

    private IngredientBean ingredient;
    /**
     * calory : 低热量
     * protein : 高蛋白
     * carbohydrate :
     * fat :
     * fiber_dietary :
     * gi :
     * gl :
     */

    private LightsBean lights;
    private int comments_ct;
    /**
     * unit_id : 34
     * amount : 1.0
     * unit : 只(中)
     * weight : 60.0
     * eat_weight : 52.8
     * calory : 86.4
     */

    private List<UnitsBean> units;
    /**
     * health_mode : 0
     * show : 1
     * light : 1
     * appraise : 鸡蛋热量及营养与生鸡蛋基本相同，但熟鸡蛋中的营养物质被人体吸收利用效率更高。
     */

    private List<HealthAppraiseBean> health_appraise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHealth_light() {
        return health_light;
    }

    public void setHealth_light(int health_light) {
        this.health_light = health_light;
    }

    public boolean isIs_liquid() {
        return is_liquid;
    }

    public void setIs_liquid(boolean is_liquid) {
        this.is_liquid = is_liquid;
    }

    public String getThumb_image_url() {
        return thumb_image_url;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
    }

    public String getLarge_image_url() {
        return large_image_url;
    }

    public void setLarge_image_url(String large_image_url) {
        this.large_image_url = large_image_url;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFiber_dietary() {
        return fiber_dietary;
    }

    public void setFiber_dietary(String fiber_dietary) {
        this.fiber_dietary = fiber_dietary;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    public Object getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(Object recipe_type) {
        this.recipe_type = recipe_type;
    }

    public IngredientBean getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientBean ingredient) {
        this.ingredient = ingredient;
    }

    public LightsBean getLights() {
        return lights;
    }

    public void setLights(LightsBean lights) {
        this.lights = lights;
    }

    public int getComments_ct() {
        return comments_ct;
    }

    public void setComments_ct(int comments_ct) {
        this.comments_ct = comments_ct;
    }

    public List<UnitsBean> getUnits() {
        return units;
    }

    public void setUnits(List<UnitsBean> units) {
        this.units = units;
    }

    public List<HealthAppraiseBean> getHealth_appraise() {
        return health_appraise;
    }

    public void setHealth_appraise(List<HealthAppraiseBean> health_appraise) {
        this.health_appraise = health_appraise;
    }

    public static class IngredientBean {
        private String calory;
        private String protein;
        private String fat;
        private String carbohydrate;
        private String fiber_dietary;
        private String vitamin_a;
        private String vitamin_c;
        private String vitamin_e;
        private String carotene;
        private String thiamine;
        private String lactoflavin;
        private String niacin;
        private String cholesterol;
        private String magnesium;
        private String calcium;
        private String iron;
        private String zinc;
        private String copper;
        private String manganese;
        private String kalium;
        private String phosphor;
        private String natrium;
        private String selenium;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getVitamin_a() {
            return vitamin_a;
        }

        public void setVitamin_a(String vitamin_a) {
            this.vitamin_a = vitamin_a;
        }

        public String getVitamin_c() {
            return vitamin_c;
        }

        public void setVitamin_c(String vitamin_c) {
            this.vitamin_c = vitamin_c;
        }

        public String getVitamin_e() {
            return vitamin_e;
        }

        public void setVitamin_e(String vitamin_e) {
            this.vitamin_e = vitamin_e;
        }

        public String getCarotene() {
            return carotene;
        }

        public void setCarotene(String carotene) {
            this.carotene = carotene;
        }

        public String getThiamine() {
            return thiamine;
        }

        public void setThiamine(String thiamine) {
            this.thiamine = thiamine;
        }

        public String getLactoflavin() {
            return lactoflavin;
        }

        public void setLactoflavin(String lactoflavin) {
            this.lactoflavin = lactoflavin;
        }

        public String getNiacin() {
            return niacin;
        }

        public void setNiacin(String niacin) {
            this.niacin = niacin;
        }

        public String getCholesterol() {
            return cholesterol;
        }

        public void setCholesterol(String cholesterol) {
            this.cholesterol = cholesterol;
        }

        public String getMagnesium() {
            return magnesium;
        }

        public void setMagnesium(String magnesium) {
            this.magnesium = magnesium;
        }

        public String getCalcium() {
            return calcium;
        }

        public void setCalcium(String calcium) {
            this.calcium = calcium;
        }

        public String getIron() {
            return iron;
        }

        public void setIron(String iron) {
            this.iron = iron;
        }

        public String getZinc() {
            return zinc;
        }

        public void setZinc(String zinc) {
            this.zinc = zinc;
        }

        public String getCopper() {
            return copper;
        }

        public void setCopper(String copper) {
            this.copper = copper;
        }

        public String getManganese() {
            return manganese;
        }

        public void setManganese(String manganese) {
            this.manganese = manganese;
        }

        public String getKalium() {
            return kalium;
        }

        public void setKalium(String kalium) {
            this.kalium = kalium;
        }

        public String getPhosphor() {
            return phosphor;
        }

        public void setPhosphor(String phosphor) {
            this.phosphor = phosphor;
        }

        public String getNatrium() {
            return natrium;
        }

        public void setNatrium(String natrium) {
            this.natrium = natrium;
        }

        public String getSelenium() {
            return selenium;
        }

        public void setSelenium(String selenium) {
            this.selenium = selenium;
        }
    }

    public static class LightsBean {
        private String calory;
        private String protein;
        private String carbohydrate;
        private String fat;
        private String fiber_dietary;
        private String gi;
        private String gl;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getGi() {
            return gi;
        }

        public void setGi(String gi) {
            this.gi = gi;
        }

        public String getGl() {
            return gl;
        }

        public void setGl(String gl) {
            this.gl = gl;
        }
    }

    public static class UnitsBean {
        private int unit_id;
        private String amount;
        private String unit;
        private String weight;
        private String eat_weight;
        private String calory;

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEat_weight() {
            return eat_weight;
        }

        public void setEat_weight(String eat_weight) {
            this.eat_weight = eat_weight;
        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }
    }

    public static class HealthAppraiseBean {
        private int health_mode;
        private int show;
        private int light;
        private String appraise;

        public int getHealth_mode() {
            return health_mode;
        }

        public void setHealth_mode(int health_mode) {
            this.health_mode = health_mode;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public String getAppraise() {
            return appraise;
        }

        public void setAppraise(String appraise) {
            this.appraise = appraise;
        }
    }
}
