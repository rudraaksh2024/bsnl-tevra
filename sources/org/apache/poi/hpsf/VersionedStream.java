package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class VersionedStream {
    private final IndirectPropertyName _streamName = new IndirectPropertyName();
    private final GUID _versionGuid = new GUID();

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this._versionGuid.read(littleEndianByteArrayInputStream);
        this._streamName.read(littleEndianByteArrayInputStream);
    }
}
