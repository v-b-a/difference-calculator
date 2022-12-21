### Hexlet tests and linter status:
[![Actions Status](https://github.com/v-b-a/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/v-b-a/java-project-71/actions)
[![Actions Status](https://github.com/v-b-a/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/v-b-a/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/eee29fecfb6199a33607/maintainability)](https://codeclimate.com/github/v-b-a/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/eee29fecfb6199a33607/test_coverage)](https://codeclimate.com/github/v-b-a/java-project-71/test_coverage)

Difference calculator is a program that perceives the difference between two data structures. Can be used in test output or automatic usage tracking in configuration files.

Utility features:

- Support for different input formats: yaml, json
- Report generation in the form of plain text, stylish and json

Usage example:
```
# формат plain
gendiff --format plain path/to/file.yml another/path/file.json

Property 'common.follow' was added with value: false
Property 'group1.baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# формат stylish
gendiff filepath1.json filepath2.json

{
  + follow: false
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {
        key: value
    }
  + setting4: blah blah
  + setting5: {
        key5: value5
    }
}
```

## Setup
```sh
make build
```

## Run
```sh
make run
```

## Run tests
```sh
make test
```

## Run checkstyle
```sh
make lint
```

## Check update dependencies and plugins
```sh
make update-deps
```


