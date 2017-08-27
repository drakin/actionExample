# Action
Dev stack:
- Spring

Execute
```
mvn jetty:run
```
Endpoints:
```
http://localhost:8088/action/add
http://localhost:8088/action/lastSecond
http://localhost:8088/action/lastMinute
http://localhost:8088/action/lastHour
http://localhost:8088/action/lastDay
```
Example content:
```
{
	"value": "test"
}
```