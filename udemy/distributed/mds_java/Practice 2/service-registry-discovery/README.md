Had problems starting greeter.

Solution:
Use Java 13.
clean compile.
---

1. If uncommented, comment out the simulated errors both in greeter and name-generator
```
//        if (Math.random() <= 0.2) {
//            throw new RuntimeException("Simulated error");
//        }
```
---

2. Make sure docker-compose was started  

```bash
..\Practice 1\docker\docker-compose -f docker-compose.yml up
```

---

3. Start all 3 applications.
We should see consul registration in terminal

`Registering service with consul: NewService{id='srd-greeter-522fa8a1-16ee-4fb5-b465-b9ea1cf93b4a', name='srd-greeter', tags=[], address='host.docker.internal', ...}`

Next check services in consul dashboard (localhost:8500)

Then we should be able to access the endpoint

```bash
curl http://localhost:8080/greet
```

or open it in a broswer.
4. Refreshing the page, we should get a random name but always the same `instanceId`.

```
Hello Kinga  from srd-greeter:fcfc274a-37a1-4c95-a553-dbb7982c840c
Hello Robert from srd-greeter:fcfc274a-37a1-4c95-a553-dbb7982c840c
...
```

5. Now start another greeter service from the console. 
Check in consul, you should see 2 greeter instances.

Sending new requests, we should now be load balanced between the services3,
resulting in alternating `instanceId`s:

```
Hello Lukasz from srd-greeter:f7278997-eaa3-4106-ae6f-7e0699862098
Hello Kinga  from srd-greeter:397746c0-cb33-4c38-aebd-8d5e2271839e
Hello Robert from srd-greeter:f7278997-eaa3-4106-ae6f-7e0699862098
Hello Ania   from srd-greeter:397746c0-cb33-4c38-aebd-8d5e2271839e
...
```

---
6. Now comment back in the simulated errors above and restart all services (Remember to package again).

Next, keep calling up the endpoint.
At some point, you should see an exception on one of the greeter services:

`java.lang.RuntimeException: Simulated error (...)`

But the service still works and returns a random name.
Since the error occured in the other service, the load balancer should in this case display the same `instanceId` (at least) twice:

```
Hello Lukasz from srd-greeter:b1172582-42cb-4fbc-aca6-a3a3f3c2ab5d
Hello Robert from srd-greeter:b1172582-42cb-4fbc-aca6-a3a3f3c2ab5d
```

