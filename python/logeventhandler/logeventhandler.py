import json
import gzip
import json
import base64
import gzip
import os
import sys
# import subprocess


#subprocess.call('pip install requests -t /tmp/ --no-cache-dir'.split())
sys.path.insert(1, '/var/task/logeventhandler/package/')
import requests


def lambda_handler(event, context):
    # Log the entire event for debugging
    print("Received event:")
    print(event)

    print(f'Logging Event: {event}')
    print(f"Awslog: {event['awslogs']}")
    cw_data = event['awslogs']['data']
    print(f'data: {cw_data}')
    print(f'type: {type(cw_data)}')
    compressed_payload = base64.b64decode(cw_data)
    uncompressed_payload = gzip.decompress(compressed_payload)
    payload = json.loads(uncompressed_payload)

    splunk_hec_url = os.environ.get('splunk_hec_url')

    log_events = payload['logEvents']
    for log_event in log_events:
        print(f'LogEvent: {log_event}')

    response = requests.post("https//www.google.com", headers={'Autorization: adasd'}, json="")


    # Extract relevant information from the log event
    # log_events = event['awslogs']['data']
    # decoded_events = boto3.client('logs').decode_log_stream(
    #     logGroupName=event['awslogs']['logGroup'],
    #     logStreamName=event['awslogs']['logStream'],
    #     logStreamToken=event['awslogs']['logStreamToken']
    # )

    # for log_event in decoded_events['Events']:
    #     message = log_event['message']
    #     # Process the log message as needed
    #     print(f"Processing log message: {message}")

    # return {
    #     'statusCode': 200,
    #     'body': 'Log event processed successfully!'
    # }


