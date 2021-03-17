import json
from s3 import S3

def trunc_file_name(file):
    return file.key.split('/')[0]

def get_root_folders(files):
    rootFolders = map(trunc_file_name, files)
    
    return list(set(rootFolders))
    
def lambda_handler(event, context):
    response = dict()
    return_code = 200

    try:
        # List content from s3
        s3 = S3('bany-bucket')
        files = s3.list_folders()
        
        # Populate response
        response['artifacts'] = get_root_folders(files)

    except Exception as e:
        # If something goes wrong
        return_code = 500
        response['error'] = str(e)
    
    response['code'] = return_code
    
    return response