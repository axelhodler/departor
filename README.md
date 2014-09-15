# WIP
Approach to access the XML serving VVS api and serve the essential parts in JSON:

```json
{
    "departures": [
        {
            "line": "U2",
            "direction": "Botnang",
            "time": "21:16",
            "route": "Neugereut - Charlottenplatz - Vogelsang - Botnang"
        },
        {
            "line": "42",
            "direction": "Erwin-Schoettle-Platz",
            "time": "21:17",
            "route": "Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz"
        }
    ]
}
```

# Build
    mvn package

# Run
    ./bin/run.sh



