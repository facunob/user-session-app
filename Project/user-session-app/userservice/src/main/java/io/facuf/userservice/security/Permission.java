package io.facuf.userservice.security;

public class Permission {

    private static class Action {

        public static final String READ = "Read";
        public static final String WRITE = "Write";
        public static final String DELETE = "Delete";
    }

    public static final class User {

        public static final String READ = "User" + "." + Action.READ;
        public static final String WRITE = "User" + "." + Action.WRITE;
        public static final String DELETE = "User" + "." + Action.DELETE;
    }



}
