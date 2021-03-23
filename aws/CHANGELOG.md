# Bany API Change log

## Version 1.0.1 - 23 Mar 2021

**Bug fixes:**

- Fixed the audio download event to prevent NullPointerException when no audio file is provided by cloudfront

## Version 1.0.0 - 20 Mar 2021

**New features:**

- Documentation

## Version 0.9.0 - 20 Mar 2021

**New features:**

- Android apk demo for testing api & cloudfront

## Version 0.8.0 - 19 Mar 2021

**New features:**

- Android apk demo for testing api & cloudfront

**Deleted features:**

- Administration tool: Non-practical due streaming conditions, direct upload to S3 is the best option
- API can no longer delete artifacts
- API can no longer post artifacts

**Changed features:**

- API has only healthcheck and Get methods

## Version 0.7.0 - 17 Mar 2021

**New features:**

-API Administration Tool
- Post & Get method does not work due to non configurable HTTP limitations of AWS API

## Version 0.6.1 - 17 Mar 2021

**Fixes:**

- Encoding fixes for PNG & FLAC binary content

## Version 0.6.0 - 17 Mar 2021

**New features:**

- API: User role: Administrator
- API: API Key role: Admin
- API: User role: Bany APP
- API: API Key role: BanyApp

- API: User role: Bany APP
- API: GET Sync Artifact enndpoint
- API: GET List Artifacts enndpoint
- API: POST Upload Artifact enndpoint
- API: DELETE Erase Artifact enndpoint

- S3: Bany bucket

## Version 0.2.0 - 14 Mar 2021

**New features:**

- API: Design & Architecture Document

## Version 0.1.0 - 14 Mar 2021

- Initial commit

**New features:**

- API: Healthcheck endpoint
