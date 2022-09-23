# Further tasks:

- write unit tests and integration tests
- implement stateless system for API key validation
- implement security configuration and token-based authentication/authorization
- extend position specification (extend Position model)
  - position mode (seeking or offering - e.g.: `/position/{id}/apply`)
  - industry field
  - skill level/experience
  - required skills, qualifications, education
  - salary
- extend search feature (e.g.: `/position/search?field=technology`:
  - search by type
  - search by field
  - search by skill level
  - search within salary range
- provide expiry date for positions
- delete positions
- match applicants with positions of the relevant field (connect tables)
  - e.g.: `applicant_field LIKE position_field`