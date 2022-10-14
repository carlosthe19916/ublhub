import React from "react";
import * as reactCore from "@patternfly/react-core";

import { Theme } from "./theme-constants";

interface IButtonAboutAppProps {
  isOpen: boolean;
  onClose: () => void;
}

export const AboutApp: React.FC<IButtonAboutAppProps> = ({
  isOpen,
  onClose,
}) => {
  return (
    <reactCore.AboutModal
      isOpen={isOpen}
      onClose={onClose}
      brandImageAlt="Brand Image"
      brandImageSrc={Theme.logoSrc}
      productName={Theme.name}
      className="about-app__component"
    >
      <reactCore.TextContent className="pf-u-py-xl">
        <h4>About</h4>
        <p>
          {Theme.name} allows application architects and developers to quickly
          decompile, analyze, assess and modernize large scale application
          portfolios and migrate them to target Runtimes, cloud and containers.
        </p>
      </reactCore.TextContent>
    </reactCore.AboutModal>
  );
};
