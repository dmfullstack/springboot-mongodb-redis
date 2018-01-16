# springboot-mongodb-redis
//1. 这里启用了一个叫“user_cache_data”的缓存，如果不指定key，则默认的规则是把参数进行拼接当做key，图中可以看得出来，这里的key就是5a****这个参数id	
	@Override
    @Cacheable(value = "user_cache_data")
    public User findUserById(String id) {
    		log.info("findUserById query from db, id: {}", id);
        return userRepository.findById(id);
    }
//2. 如果我们显示指定了key值,那么看图2中这个换名的名字就叫做user_cache_data_haskey，然后里面的key-value键值对的key是字符串user5d*******
	@Override
    @Cacheable(value = "user_cache_data_haskey", key = "'user'.concat(#id.toString())")
    public User findUserById(String id) {
    		log.info("findUserById query from db, id: {}", id);
        return userRepository.findById(id);
    }
//3. 第三种方法，自定义这个key值，下面可以看到这个是以一个class,method,然后加上参数的方式去定义这个key，这样基本不会重复的key值：prefix:user_cache_data_haskey:
//在用到的地方需要：

	@Override
    @Cacheable(value = "user_cache_data_haskey", keyGenerator = "customKeyGenerator")
    public User findUserById(String id) {
    		log.info("findUserById query from db, id: {}", id);
        return userRepository.findById(id);
    }

//自定义的key值
com.data.analysis.service.impl.UserServiceImplfindUserById5a5cccf5570a202283071b5d
	  @Bean
	  public KeyGenerator customKeyGenerator() {
	    return new KeyGenerator() {
	      @Override
	      public Object generate(Object o, Method method, Object... objects) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(o.getClass().getName());
	        sb.append(method.getName());
	        for (Object obj : objects) {
	          sb.append(obj.toString());
	        }
	        return sb.toString();
	      }
	    };
	  }
