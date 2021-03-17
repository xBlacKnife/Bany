import json
from s3 import S3

def valid_request(event):
    name = event.get('name')
    return name and len(name) > 0

def lambda_handler(event, context):
    response = dict()
    return_code = 200

    # Validate delete request
    if valid_request(event):
        try:
            s3 = S3('bany-bucket')

            # Delete s3 content
            if s3.folder_exists(event.get('name')):
                s3.delete_folder(event.get('name'))
            else:
                return_code = 404
    
        except Exception as e:
            return_code = 500
            response['error'] = str(e)
    else:
        return_code = 400

    response['code'] = return_code
    return response
