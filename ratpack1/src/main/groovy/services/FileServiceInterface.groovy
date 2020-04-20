package services

import ratpack.exec.Promise
import ratpack.service.Service

interface FileServiceInterface extends Service {

    Promise<String> DummyPromiseSleepAppend1()
    Promise<String> PromiseSleepAppend1()

    /* --------------
    Promise<Long> lpush(String key, String value);

    Promise<String> rpoplpush(String source, String destination);

    Promise<String> rpop(String key);

    Promise<String> set(String key, String value);

    Promise<String> set(String key, String value, int expiry);

    Promise<Long> sadd(String key, String value);

    Promise<Long> llen(String key);

    Promise<Set<String>> smembers(String key);

    Promise<String> get(String key);

    Promise<Long> del(String key);

    Promise<Long> srem(String key, String value);
-------------------*/

}