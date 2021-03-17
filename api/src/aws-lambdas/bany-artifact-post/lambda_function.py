import json
from s3 import S3


def valid_request(event):
    name = event.get('name')
    return name and len(name) > 0
    
def capture_file_content(event, prop):
    content = event.get(prop)
    if not content:
        content = ''
    return content
    
def upload_file(event, s3, name, prop, ext):
    content = capture_file_content(event, prop)
    if len(content) > 0:
        s3.upload_file('{0}/{1}.{2}'.format(name, prop, ext), bytes(content, 'utf-8') )
    
def lambda_handler(event, context):
    response = dict()
    return_code = 200

    # Validate post body
    if valid_request(event):
        name = event.get('name')

        try:
            # Upload content to s3
            s3 = S3('bany-bucket')
            
            if not s3.object_exists(name):
                upload_file(event, s3, name, 'description', 'txt')
                upload_file(event, s3, name, 'image', 'png')
                upload_file(event, s3, name, 'audio', 'flac')
            else:
                # If content already exists
                return_code = 409
                response['warn'] = 'Resource: {}, already exists!'.format(name)
    
        except Exception as e:
            # If something goes wrong
            return_code = 500
            response['error'] = str(e)
    else:
        # Bad request
        return_code = 400
    
    response['code'] = return_code
    
    return response