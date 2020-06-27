<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <xsl:param name="url"/>
    <xsl:param name="code"/>
    <breedItem>
      <url>
        <xsl:value-of select="$url"/>
      </url>
      <code>
        <xsl:value-of select="$code"/>
      </code>
      <availableUrl>
        <xsl:value-of select="//a[@class='txt txt_link m-txt_lg m-txt_underline']/@href"/>
      </availableUrl>
      <breedName>
        <xsl:value-of select="//*[@data-test='Breed_Details_Name']"/>
      </breedName>
      <imageUrl>
        <xsl:value-of select="//*[@class='grid grid_gutterLg']//img/@src"/>
      </imageUrl>
      <description>
        <xsl:value-of select="//*[@class='txt m-txt_lg u-vr6x']/p"/>
      </description>
      <traits>
        <xsl:for-each select="//*[@class='txt m-txt_heavy m-txt_uppercase u-vr1x']">
          <item>
            <name>
              <xsl:value-of select="."/>
            </name>
            <value>
              <xsl:value-of select="./following-sibling::*[@class='meterBar'][1]//span"/>
            </value>
          </item>
        </xsl:for-each>
      </traits>
      <attributes>
        <xsl:for-each select="//*[@class='txt m-txt_heavy m-txt_sm m-txt_uppercase u-vr1x']">
          <item>
            <name>
              <xsl:value-of select="."/>
            </name>
            <value>
              <xsl:value-of select="./following-sibling::p[1]"/>
            </value>
          </item>
        </xsl:for-each>
      </attributes>
      <sections>
        <xsl:for-each select="//*[@class='txt txt_h3 u-vr4x']">
          <item>
            <name>
              <xsl:value-of select="."/>
            </name>
            <value>
              <xsl:value-of select="./following-sibling::div[1]"/>
            </value>
          </item>
        </xsl:for-each>
      </sections>
    </breedItem>
  </xsl:template>
</xsl:stylesheet>