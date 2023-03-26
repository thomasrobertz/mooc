rm -f ../generated/*
python3 -m grpc_tools.protoc -I./ --python_out=../generated --pyi_out=../generated --grpc_python_out=../generated connection_protocol.proto