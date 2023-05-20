package com.chris.demo.collection.map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.chris.demo.entities.Employee;
import com.chris.demo.entities.Student;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class GuavaMapTest {


    /**
     * java中的Map只允许有一个key和一个value存在，
     * 但是guava中的Table允许一个value存在两个key。Table中的两个key分别被称为rowKey和columnKey
     * <p>
     * result:
     * <p>
     * dayCount:28
     * rowKeySet:[Hydra, Trunks], columnKeySet:[Jan, Feb], value:[20, 28, 28, 16]
     * Hydra'total is 48
     * Trunks'total is 44
     * rowKeySet:[Jan, Feb], columnKeySet:[Hydra, Trunks], value:[20, 28, 28, 16]
     * table size:4
     * transpose size:4
     * cell:Jan-Hydra-20
     * cell:Feb-Hydra-28
     * cell:Jan-Trunks-28
     * cell:Feb-Trunks-16
     * rowMap:{"Hydra":{"Jan":20,"Feb":28},"Trunks":{"Jan":28,"Feb":16}}
     * columnMap:{"Jan":{"Hydra":20,"Trunks":28},"Feb":{"Hydra":28,"Trunks":16}}
     * rowKeySet:[Hydra, Trunks], columnKeySet:[Jan, Feb], value:[20, 28, 28, 16]
     */
    @Test
    public void testTable() {
        Table<String, String, Integer> table = HashBasedTable.create();
        // 存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);
        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        // 取出元素
        Integer dayCount = table.get("Hydra", "Feb");
        log.info("dayCount:{}", dayCount);

        showDetail(table);

        // 计算key对应的所有value的和
        for (String s : table.rowKeySet()) {
            Set<Map.Entry<String, Integer>> entries = table.row(s).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> entry : entries) {
                total += entry.getValue();
            }
            log.info("{}'total is {}", s, total);
        }

        // 转换rowKey和columnKey
        Table<String, String, Integer> transpose = Tables.transpose(table);
        showDetail(transpose);

        log.info("table size:{}", table.size());
        log.info("transpose size:{}", transpose.size());

        // 利用cellSet方法可以得到所有的数据行，打印结果，可以看到row和column发生了互换
        Set<Table.Cell<String, String, Integer>> cells = transpose.cellSet();
        cells.forEach(cell -> log.info("cell:{}", String.join(StrUtil.DASHED, cell.getRowKey(), cell.getColumnKey(), String.valueOf(cell.getValue()))));


        // 转为嵌套的Map
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        log.info("rowMap:{}", JSONUtil.toJsonStr(rowMap));
        log.info("columnMap:{}", JSONUtil.toJsonStr(columnMap));

        showDetail(table);
    }

    private static void showDetail(Table<String, String, Integer> table) {
        Set<String> rowKeySet = table.rowKeySet();
        Set<String> columnKeySet = table.columnKeySet();
        Collection<Integer> values = table.values();
        log.info("rowKeySet:{}, columnKeySet:{}, value:{}", rowKeySet, columnKeySet, values);
    }

    /**
     * 1. 在普通Map中，如果要想根据value查找对应的key，没什么简便的办法，无论是使用for循环还是迭代器，都需要遍历整个Map,而guava中的BiMap提供了一种key和value双向关联的数据结构
     * 2. 用inverse方法反转了原来BiMap的键值映射，但是这个反转后的BiMap并不是一个新的对象，它实现了一种视图的关联，所以对反转后的BiMap执行的所有操作会作用于原先的BiMap上;
     * 3. BiMap的底层继承了Map，我们知道在Map中key是不允许重复的，而双向的BiMap中key和value可以认为处于等价地位，因此在这个基础上加了限制，value也是不允许重复的,
     * 3.1. 结果是如果key重复会新的替换旧的，如果value重复会报错
     * 3.2. 如果你非想把新的key映射到已有的value上，那么也可以使用forcePut方法强制替换掉原有的key.
     * 4. 由于BiMap的value是不允许重复的，因此它的values方法返回的是没有重复的Set，而不是普通Collection
     * <p>
     * result:
     * <p>
     * inverse:{"Programmer":"Hydra","Titan":"Thanos","IronMan":"Chris","BatMan":"John"}
     * biMap:{"Hydra":"Programmer","Thanos":"Titan","Chris":"IronMan","John":"BatMan"}
     * biMap:{"Hydra":"Programmer","Thanos":"Titan","John":"IronMan"}
     * values:["Programmer","Titan","IronMan"]
     */

    @Test
    public void testBiMap() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra", "Programmer");
        biMap.put("Tony", "IronMan");
        biMap.put("Thanos", "Titan");
        // 使用key获取value
        System.out.println(biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
        // 使用value获取key
        System.out.println(inverse.get("Titan"));

        inverse.put("IronMan", "Chris");
        inverse.put("BatMan", "Tony");
        inverse.put("BatMan", "John");


        log.info("inverse:{}", JSONUtil.toJsonStr(inverse));// inverse:{"Programmer":"Hydra","Titan":"Thanos","IronMan":"Chris","BatMan":"John"}
        log.info("biMap:{}", JSONUtil.toJsonStr(biMap)); // biMap:{"Hydra":"Programmer","Thanos":"Titan","Chris":"IronMan","John":"BatMan"}

        /**
         * java.lang.IllegalArgumentException: value already present: IronMan
         */
        // biMap.put("John", "IronMan");
        biMap.forcePut("John", "IronMan");
        log.info("biMap:{}", JSONUtil.toJsonStr(biMap)); // biMap:{"Hydra":"Programmer","Thanos":"Titan","John":"IronMan"}

        // 由于BiMap的value是不允许重复的，因此它的values方法返回的是没有重复的Set，而不是普通Collection
        Set<String> values = biMap.values();
        log.info("values:{}", JSONUtil.toJsonStr(values));

    }


    /**
     * java中的Map维护的是键值一对一的关系，如果要将一个键映射到多个值上，那么就只能把值的内容设为集合形式:
     * Map<String, List<Integer>> map=new HashMap<>();
     * <p>
     * guava中的Multimap提供了将一个键映射到多个值的形式，使用起来无需定义复杂的内层集合，可以像使用普通的Map一样使用它
     */
    @Test
    public void testMultimap() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        populateValue(multimap);
        log.info("multimap:{}", JSONUtil.toJsonStr(multimap));

        Collection<Integer> key_day = multimap.get("day");
        log.info("key_day:{}", JSONUtil.toJsonStr(key_day));

        /**
         * 如果在创建时指定为ArrayListMultimap类型，那么get方法将返回一个List
         * 可以创建HashMultimap、TreeMultimap等类型的Multimap
         * Multimap的get方法会返回一个非null的集合，但是这个集合的内容可能是空
         */

        ArrayListMultimap<String, Integer> listMultimap = ArrayListMultimap.create();
        populateValue(listMultimap);
        log.info("listMultimap:{}", JSONUtil.toJsonStr(listMultimap.toString()));

        List<Integer> day = listMultimap.get("day");
        List<Integer> month = listMultimap.get("month");
        log.info("day1:{}", JSONUtil.toJsonStr(day));

        /**
         * 和BiMap的使用类似，使用get方法返回的集合也不是一个独立的对象，可以理解为集合视图的关联，对这个新集合的操作仍然会作用于原始的Multimap上
         */
        day.remove(0);
        month.add(12);
        log.info("listMultimap:{}", JSONUtil.toJsonStr(listMultimap.toString()));

        // 使用asMap方法，可以将Multimap转换为Map<K,Collection>的形式
        Map<String, Collection<Integer>> collectionMap = listMultimap.asMap();
        collectionMap.forEach((k, values) -> log.info("k:{}, values:{}", k, JSONUtil.toJsonStr(values)));

        /**
         * 数量问题
         * size()方法返回的是所有key到单个value的映射
         * entries()方法同理，返回的是key和单个value的键值对集合
         * 是它的keySet中保存的是不同的key的个数
         */
        log.info("listMultiMap size:{}", multimap.size());
        log.info("multimap.entries size:{}", multimap.entries().size());

        for (Map.Entry<String, Integer> entry : multimap.entries()) {
            log.info("key:{}, value:{}", entry.getKey(), entry.getValue());
        }
        log.info("keySet:{}", listMultimap.keySet());
        log.info("values:{}", listMultimap.values());


    }

    private static void populateValue(Multimap<String, Integer> multimap) {
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);
    }


    /**
     * guava中的RangeMap描述了一种从区间到特定值的映射关系
     * if (0<=score && score<60)
     * return "fail";
     * else if (60<=score && score<=90)
     * return "satisfactory";
     * else if (90<score && score<=100)
     * return "excellent";
     * return null;
     * <p>
     * result:
     * <p>
     * rangeMap:[[0..60)=fail, [60..90]=satisfactory, (90..100]=excellent]
     * fail
     * satisfactory
     * satisfactory
     * excellent
     * rangeMap:[[0..60)=fail, [60..70)=satisfactory, (80..90]=satisfactory, (90..100]=excellent]
     * null
     */
    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0, 60), "fail");
        rangeMap.put(Range.closed(60, 90), "satisfactory");
        rangeMap.put(Range.openClosed(90, 100), "excellent");

        log.info("rangeMap:{}", JSONUtil.toJsonStr(rangeMap.toString()));

        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));

        rangeMap.remove(Range.closed(70, 80));
        log.info("rangeMap:{}", JSONUtil.toJsonStr(rangeMap.toString()));

        System.out.println(rangeMap.get(75));
    }


    /**
     * ClassToInstanceMap是一个比较特殊的Map，它的键是Class，而值是这个Class对应的实例对象
     * 如果你想缓存对象，又不想做复杂的类型校验，那么使用方便的ClassToInstanceMap就可以了。
     * <p>
     * 取出对象时省去了复杂的强制类型转换，避免了手动进行类型转换的错误
     * <p>
     * 泛型同样可以起到对类型进行约束的作用
     * 因为HashMap和TreeMap都集成了Map父类，但是如果想放入其他类型，就会编译报错：
     * <p>
     * java: 不兼容的类型: java.lang.Class<java.util.ArrayList>无法转换为java.lang.Class<? extends java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Test
    public void testClassToInstanceMap() {
        ClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        Employee employee = new Employee(18, "Hydra");
        Student student = new Student("chris", "math", 200);

        instanceMap.putInstance(Employee.class, employee);
        instanceMap.putInstance(Student.class, student);

        Employee instance = instanceMap.getInstance(Employee.class);
        log.info("instance:{}", JSONUtil.toJsonStr(instance));


        ClassToInstanceMap<Map<String, Object>> instanceMap2 = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();

        instanceMap2.putInstance(HashMap.class, hashMap);
        instanceMap2.putInstance(TreeMap.class, treeMap);

        // instanceMap2.put(ArrayList.class, list);
    }
}
