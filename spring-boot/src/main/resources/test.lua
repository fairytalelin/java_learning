if not redis.call('get', KEYS[1])
then
    redis.call('incr',KEYS[1])
    redis.call('EXPIRE',KEYS[1],ARGV[1])
    return tonumber(redis.call('get', KEYS[1]))
else if(tonumber(redis.call('get',KEYS[1])) >= tonumber(ARGV[2]))
    then
        return 0
    else
        redis.call('incr',KEYS[1])
        return tonumber(redis.call('get', KEYS[1]))
    end
end