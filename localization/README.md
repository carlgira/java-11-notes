# Localization
Application internationalization

Oracle defines a locale as a:
- geographical region
- political region
- cultural region.

- Region and language aware software
- Dates, numbers and currencies
- Ability to plug in country-specific data without changing code.

- Language:
  - An alpha-2 or alpha-3 ISO 639 code
  - "en" for English or "es" Spanish
  - lowercase

- Country
  - Uses the ISO 3166 alpha-2 country code
  - "US" for United States "ES" for Spain

### Properties

- get(String key)
- getProperty(String key, String defaultValue)

### Locale

- new Locale(String language)
- new Locale(String language, String country)
- new Locale(String language, String country, String variant)
- static getDefault()
- static setDefault(Locale)
- void setDefault(Category, Locale)
  - DISPLAY (Category)
    Category used to represent the default locale for displaying user interfaces.
  - FORMAT (Category)
    Category used to represent the default locale for formatting dates, numbers, and/or currencies.

### Resources Bundle

- Resource bundle checks on descending order the locales
  - First looks for the parameter locale,
  - Looks for file with "language_COUNTRY"
  - If does not find it, "language"
  - If does not find it, got for the "" file.
  - If does not find it, goes for the default locale
  - Looks for file with "language_COUNTRY" of the default
  - If does not find it "language" of the default
  - If does not find it, got for the "" file.
  - If does not find it fails with MissingResourceException.

- Label_pl_PL_UNIX (configured)
- Label_pl_PL
- Label_pl
- Label_en_US (default)
- Label_en
- Label


### DateTimeFormater
- static ofLocalizedDateTime();
- static ofLocalizedDate();
- static ofLocalizedTime();
- static ofPattern()

To get time there are the classes **LocalTime** and **LocalDateTime** using format() method
- static of()
- static now()
- static parse()
- format()

### NumberFormat
- static getNumberInstance(Locale)
- static getPercentInstance(Locale)
- static getCurrencyInstance(Locale)
- static getInstance(Locale)
- format()
- parse()

### DecimalFormat


- 0 – prints a digit if provided, 0 otherwise
- \# – prints a digit if provided, nothing otherwise
- . – indicate where to put the decimal separator
- , – indicate where to put the grouping separator


# Links
- https://www.baeldung.com/java-8-localization
- https://www.baeldung.com/java-resourcebundle
- https://www.baeldung.com/java-decimalformat


Question Notes:
- Locales has an order, if the one search for not found, going for default.
- Default locale can be defined:
  - Locale.setDefault()
  - Or the file without language or country specifications.
- On DateTimeFormatter the single quotes define not formatted text. Careful with bad formated strings.
- Locale constructors call to lowercase on language and uppercase on country.
- Format a LocalDate to LocalDateTime throws an Exception.
- DateTimeFormater does not have format method.
- NumberFormat have a format method.
- There is a Locale.Builder
- Property no Properties
- Locale don’t do validation of inputs


