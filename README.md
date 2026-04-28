# GIM Shed

A small RuneLite plugin that renames the **Group Storage** interface title to
something friendlier — defaults to "The Shed", configurable in the plugin
panel.

Affects only the storage interface's title bar text. The right-click menu on
the chest object is unchanged.

## Configuration

| Option              | Default     | Description                                   |
|---------------------|-------------|-----------------------------------------------|
| Replacement title   | `The Shed`  | What to rename `Group Storage` to             |

## Building

```
./gradlew build
```

Requires JDK 11.

## License

BSD 2-Clause. See [LICENSE](LICENSE).
