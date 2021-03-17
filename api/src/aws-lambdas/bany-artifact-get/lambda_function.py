import json
from s3 import S3


def valid_request(event):
    name = event.get('name')
    return name and len(name) > 0
    
def read_files(event, s3, name, response):
    dFile = '{0}/description.txt'.format(name)
    iFile = '{0}/image.png'.format(name)
    aFile = '{0}/audio.flac'.format(name)
    
    if s3.object_exists(dFile):
        response['description'] = s3.read_file(dFile)
    if s3.object_exists(iFile):
        response['image'] = s3.read_file(iFile)
    if s3.object_exists(aFile):
        response['audio'] = s3.read_file(aFile)
    
def lambda_handler(event, context):
    response = dict()
    return_code = 200

    # Validate get body
    if valid_request(event):
        name = event.get('name')

        try:
            s3 = S3('bany-bucket')

            # Read s3 content
            if s3.object_exists(name):
                read_files(event, s3, name, response)
            else:
                return_code = 404
    
        except Exception as e:
            return_code = 500
            response['error'] = str(e)
    else:
        return_code = 400
    
    response['code'] = return_code
    
    return response