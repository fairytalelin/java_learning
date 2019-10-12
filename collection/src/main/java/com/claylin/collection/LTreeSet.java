package com.claylin.collection;

import java.util.Comparator;
import java.util.TreeSet;

public class LTreeSet {
    public static void main(String[] args) {
        SortedUser s1 = new SortedUser();
        s1.setAge(11);
        s1.setNickName("claylin");
        s1.setUid(2200806714L);

        SortedUser s2 = new SortedUser();
        s2.setAge(22);
        s2.setNickName("fairytalelin");
        s2.setUid(2659874555L);


        SortedUser s3 = new SortedUser();
        s3.setAge(22);
        s3.setNickName("Gfairytalelin");
        s3.setUid(2659874551L);


        SortedUser s4 = new SortedUser();
        s4.setAge(22111);
        s4.setNickName("fairytalelin");
        s4.setUid(265987451L);

        TreeSet<SortedUser> set = new TreeSet<>(new UsrComparator());
        //set.add(s1);
        //set.add(s2);

        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);

        set.forEach(System.out::println);

    }

    public static class UsrComparator implements Comparator<SortedUser> {
        @Override
        public int compare(SortedUser o1, SortedUser o2) {

            int age1 = o1.getAge();
            int age2 = o2.getAge();

            if (age1 > age2) {
                return -1;
            }

            if (age1 < age2) {
                return 1;
            }

            long uid1 = o1.getUid();
            long uid2 = o2.getUid();
            String name1 = o1.getNickName();
            String name2 = o2.getNickName();

            if (name1.compareToIgnoreCase(name2) == 0) {
                return -((int) (uid1 - uid2));
            }


            return -(name1.compareToIgnoreCase(name2));
        }
    }

    public static class SortedUser {
        private long uid;
        private String nickName;
        private int age;


        public long getUid() {
            return uid;
        }

        public SortedUser setUid(long uid) {
            this.uid = uid;
            return this;
        }

        public String getNickName() {
            return nickName;
        }

        public SortedUser setNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public int getAge() {
            return age;
        }

        public SortedUser setAge(int age) {
            this.age = age;
            return this;
        }

        @Override
        public String toString() {
            return "SortedUser{" +
                    "uid=" + uid +
                    ", nickName='" + nickName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
