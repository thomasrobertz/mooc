# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: item.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\nitem.proto\"K\n\x0bItemMessage\x12\x0c\n\x04name\x18\x01 \x01(\t\x12\x12\n\nbrand_name\x18\x02 \x01(\t\x12\n\n\x02id\x18\x03 \x01(\x05\x12\x0e\n\x06weight\x18\x04 \x01(\x02\x32\x33\n\x0bItemService\x12$\n\x06\x43reate\x12\x0c.ItemMessage\x1a\x0c.ItemMessageb\x06proto3')



_ITEMMESSAGE = DESCRIPTOR.message_types_by_name['ItemMessage']
ItemMessage = _reflection.GeneratedProtocolMessageType('ItemMessage', (_message.Message,), {
  'DESCRIPTOR' : _ITEMMESSAGE,
  '__module__' : 'item_pb2'
  # @@protoc_insertion_point(class_scope:ItemMessage)
  })
_sym_db.RegisterMessage(ItemMessage)

_ITEMSERVICE = DESCRIPTOR.services_by_name['ItemService']
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  _ITEMMESSAGE._serialized_start=14
  _ITEMMESSAGE._serialized_end=89
  _ITEMSERVICE._serialized_start=91
  _ITEMSERVICE._serialized_end=142
# @@protoc_insertion_point(module_scope)